package com.linsh.lshutils.entity;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2019/10/11
 *    desc   :
 * </pre>
 */
public interface NestedInfo {

    boolean isNested();

    boolean isOpened();

    void setOpened(boolean isOpened);

    List<? extends NestedInfo> getChildren();
}