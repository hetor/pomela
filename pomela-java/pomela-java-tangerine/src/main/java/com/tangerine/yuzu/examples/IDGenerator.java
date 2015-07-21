package com.tangerine.yuzu.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IDGenerator implements Runnable{
    
    private Connection conn = DBUtil.createConnection();

    @Override
    public void run() {
        int i = 10;
        while(i-- > 0) {
            //key from database
            int key = getKeyFromDB();
            key++;
            //update key to database;
            updateKeyToDB(key);
            //insert op
            insertToDB(key);
        }
    }
    
    private void insertToDB(int id) {
        try {
            System.out.println("#" + Thread.currentThread().getId() + " id:" + id);
            PreparedStatement pst = conn.prepareStatement("insert into ids values(,?);");
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int getKeyFromDB() {
        try {
            PreparedStatement pst = conn.prepareStatement("select * from keys_ where t_name=?");
            pst.setString(1, "ids");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println("#"+Thread.currentThread().getId() + " key: " + rs.getInt("key_"));
                return rs.getInt("key_");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private void updateKeyToDB(int key) {
        try {
            PreparedStatement pst = conn.prepareStatement("update keys_ set key_ = ? where t_name=?");
            pst.setInt(1, key);
            pst.setString(2, "ids");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String args[]) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i<10; i++) {
            exec.execute(new IDGenerator());
        }
    }
}


class KeyTask implements Runnable {

    @Override
    public void run() {
        
    }
}



