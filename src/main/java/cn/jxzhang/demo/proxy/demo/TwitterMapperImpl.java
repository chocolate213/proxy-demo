package cn.jxzhang.demo.proxy.demo;

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
}
