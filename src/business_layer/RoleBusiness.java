/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.RoleDB;
import model.RoleModel;

/**
 * Business class of QLPhanQuyen
 */
public class RoleBusiness {
    private final RoleDB db = new RoleDB();
    
    /**
     * Get login info from QLPhanQuyen table
     * @param userName user name
     * @param password password
     * @return RoleModel
     */
    public RoleModel getLoginInfo(String userName, String password) {
        return db.getLoginInfo(userName, password);
    }
    
    /**
     * Update password
     * @param account account
     * @param oldPassword old password
     * @param newPassword new password
     * @return row effected
     */
    public int updatePassword(String account, String oldPassword, String newPassword) {
        return db.updatePassword(account, oldPassword, newPassword);
    }
}
