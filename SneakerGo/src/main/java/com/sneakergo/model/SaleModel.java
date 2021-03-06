package com.sneakergo.model;

import com.sneakergo.common.constants.SQLParamConstant;
import com.sneakergo.common.utils.NumbericUtils;
import com.sneakergo.entity.SaleDisplayEntity;
import com.sneakergo.entity.SaleEntity;
import com.sneakergo.model.common.CommonDAO;
import com.sneakergo.model.interfaces.SaleModelInterface;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Hung on 11/20/2016.
 */
@Repository
public class SaleModel extends CommonDAO implements SaleModelInterface {

    @Override
    public SaleEntity getSaleByID(int saleID) {
        return getSession().get(SaleEntity.class, saleID);
    }

    @Override
    public List<SaleDisplayEntity> getSaleByTime(Date time) {
        Query query=getSession().createSQLQuery(SQLParamConstant.GET_ALL_SALE_IN_TIME).
                setParameter("date",time)
                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List<SaleDisplayEntity> sales = query.list();
        return sales;
    }

    @Override
    public List<SaleEntity> getAllSale() {
        Criteria criteria = getSession().createCriteria(SaleEntity.class).add(Restrictions.eq("enabled", true)).
                addOrder(Order.desc("toDate"));
        List<SaleEntity> sales = criteria.list();
        return sales;
    }

    @Override
    public List<SaleEntity> getSaleContainsToday() {
        Criteria criteria = getSession().createCriteria(SaleEntity.class).add(Restrictions.eq("enabled", true)).
                add(Restrictions.lt("toDate", NumbericUtils.getCurrentDate())).addOrder(Order.desc("toDate"));
        List<SaleEntity> sales = criteria.list();
        return sales;
    }

    @Override
    public boolean createSave(SaleEntity saleEntity) {
        if (getSaleByID(saleEntity.getSaleId()) == null) {
            getSession().persist(saleEntity);
            return true;
        }
        return false;
    }
}
