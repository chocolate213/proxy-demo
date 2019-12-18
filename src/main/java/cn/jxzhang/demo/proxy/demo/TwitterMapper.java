package cn.jxzhang.demo.proxy.demo;

import java.util.Collections;
import java.util.List;

/**
 * TwitterMapper
 *
 * @author zhangjiaxing
 */
public interface TwitterMapper {

    String selectOne(Integer id);

    default List<String> selectList() {
        System.out.println("default run...");
        return Collections.emptyList();
    }
}
