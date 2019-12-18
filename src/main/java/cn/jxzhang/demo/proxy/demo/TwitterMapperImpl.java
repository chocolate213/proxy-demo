package cn.jxzhang.demo.proxy.demo;

import java.util.List;

/**
 * TwitterMapperImpl
 *
 * @author zhangjiaxing
 */
public class TwitterMapperImpl implements TwitterMapper {
    @Override
    public String selectOne(Integer id) {
        return String.valueOf(id);
    }

    @Override
    public List<String> selectList() {
        return TwitterMapper.super.selectList();
    }

    public String selectInSuper() {
        return "SUCCESS";
    }
}
