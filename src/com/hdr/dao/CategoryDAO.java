package com.hdr.dao;

import com.hdr.Bean.Category;
import com.hdr.util.DBUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    //获取分类总数
    public static int getTotal(){
        int total = 0;
        try(
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement();
                ) {
            String sql = "select count(*) from Category";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    //添加分类
    public static void add(Category bean){
        String sql = "insert into category values(null,?)";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            ps.setString(1,bean.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据id删除分类
    public static void delete(int id){
        try(
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement()
                ) {
            String sql = "delete from category where id = " + id;
            s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据id获取分类
    public static Category get(int id){
        Category bean = null;
        try(
                Connection c = DBUtil.getConnection();
                Statement s = c.createStatement()
                ){
            String sql = "select * from category where id = "+id;
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                bean = new Category();
                String name = rs.getString(2);
                bean.setName(name);
                bean.setId(id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return bean;
    }
    //获取部分分类
    public static List<Category> list(int start,int count){
        List<Category> beans = new ArrayList<>();
        String sql = "select * from category order by id desc limit ?,?";
        try(
                Connection c = DBUtil.getConnection();
                PreparedStatement ps = c.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)
                ){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Category bean = new Category();
                bean.setId(id);
                bean.setName(name);
                beans.add(bean);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return beans;
    }
    //获取所有分类
    public static List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }

    public static void main(String[] args) {
        System.out.println("分类总数: "+getTotal());
        System.out.println("添加衣服分类");
        Category c = new Category();
        c.setName("衣服");
        add(c);
        System.out.println("分类总数: "+getTotal());

        System.out.println("根据id查询分类");
        System.out.println(get(1).getName());

        System.out.println("查询部分分类");
        System.out.println(list(0,1));

        System.out.println("查询全部分类");
        System.out.println(list());

        System.out.println("删除衣服分类");
        delete(1);
        System.out.println("分类总数: "+getTotal());
    }
}
