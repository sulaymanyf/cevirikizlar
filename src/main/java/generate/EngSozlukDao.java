package generate;

import generate.EngSozluk;

public interface EngSozlukDao {
    int insert(EngSozluk record);

    int insertSelective(EngSozluk record);
}