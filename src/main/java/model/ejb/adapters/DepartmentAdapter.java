package model.ejb.adapters;

import model.dao.DepartmentDAOImpl;
import model.entity.Department;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DepartmentAdapter extends XmlAdapter<Long, Department> {
    @Override
    public Department unmarshal(Long v) throws Exception {
        return new DepartmentDAOImpl().get(v);
    }

    @Override
    public Long marshal(Department v) throws Exception {
        return v.getId();
    }
}