/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalib;

/**
 *
 * @author leo
 */
public class Toolet {
    public String getHomePath(){
        return getClass().getResource("/").getPath().replaceAll("classes/", "").replaceAll("%20", "\\ ");
    }
}
