package com.kpi.dao;

import com.kpi.models.MenuElement;


public interface MenuElementDao extends AbstractDAO<MenuElement> {
    void updateImage(int id, byte[] buf);
    void updateDetails(MenuElement a);
}
