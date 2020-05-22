package model.ejb.adapters;

import model.dao.EmployeeDAOImpl;
import model.entity.Employee;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ManagerAdapter extends XmlAdapter<Long, Employee> {
    @Override
    public Employee unmarshal(Long v) throws Exception {
        return new EmployeeDAOImpl().get(v, false);
    }

    @Override
    public Long marshal(Employee v) throws Exception {
        return v.getId();
    }
}