package model.ejb.adapters;

import model.dao.PositionDAOImpl;
import model.entity.Position;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PositionAdapter extends XmlAdapter<Long, Position> {
    @Override
    public Position unmarshal(Long v) throws Exception {
        return new PositionDAOImpl().get(v);
    }

    @Override
    public Long marshal(Position v) throws Exception {
        return v.getId();
    }
}