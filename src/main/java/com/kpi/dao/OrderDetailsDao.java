package com.kpi.dao;

import com.kpi.models.OrderDetails;
import java.util.ArrayList;

public interface OrderDetailsDao extends AbstractDAO<OrderDetails>{
    void deleteByMenuElementId(int id);
    ArrayList<OrderDetails> getAllByOrdersId(int id);

}
