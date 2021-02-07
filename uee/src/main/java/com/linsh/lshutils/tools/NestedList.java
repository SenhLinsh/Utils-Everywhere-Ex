package com.linsh.lshutils.tools;

import com.linsh.lshutils.entity.NestedInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2021/02/07
 *    desc   : 对 NestedInfo 进行封装扁平化为 List
 * </pre>
 */
public class NestedList {

    // NestedInfo 源数据
    private List<? extends NestedInfo> source;
    // 记录每一个 Item 在 data 中的位置
    private final List<int[]> items = new ArrayList<>();

    public NestedList(List<? extends NestedInfo> source) {
        setNestedInfos(source);
    }

    /**
     * 设置 NestedInfo 源数据
     */
    public void setNestedInfos(List<? extends NestedInfo> source) {
        this.source = source;
        items.clear();
        if (source != null) {
            for (int i = 0; i < source.size(); i++) {
                int[] positions = {i};
                items.add(positions);
                checkOpen(source.get(i), positions);
            }
        }
    }

    /**
     * 检查已经打开的 NestedInfo
     */
    private void checkOpen(NestedInfo t, int[] positions) {
        if (!t.isOpened()) return;
        List<? extends NestedInfo> children = t.getChildren();
        for (int i = 0; i < children.size(); i++) {
            int[] childPositions = Arrays.copyOf(positions, positions.length + 1);
            childPositions[childPositions.length - 1] = i;
            items.add(childPositions);
            NestedInfo child = children.get(i);
            checkOpen(child, childPositions);
        }
    }

    /**
     * NestedInfo 列表大小
     */
    public int size() {
        return items.size();
    }

    /**
     * 根据位置获取 NestedInfo 在列表中的位置
     *
     * @param position NestedInfo 的位置
     * @return NestedInfo
     */
    public NestedInfo get(int position) {
        int[] positions = items.get(position);
        NestedInfo t = null;
        for (int i = 0; i < positions.length; i++) {
            if (i == 0) {
                t = source.get(positions[i]);
            } else {
                t = t.getChildren().get(positions[i]);
            }
        }
        return t;
    }

    /**
     * 切换 NestedInfo 的打开/关闭状态
     *
     * @param position NestedInfo 的位置
     */
    public void toggle(int position) {
        NestedInfo nestedInfo = get(position);
        if (!nestedInfo.isNested())
            return;
        boolean opened = nestedInfo.isOpened();
        if (opened) {
            // 关闭 Item
            nestedInfo.setOpened(false);
            int[] positions = items.get(position);
            int next = position + 1;
            while (next < items.size() && items.get(next).length > positions.length) {
                items.remove(next);
            }
        } else {
            // 打开 Item
            nestedInfo.setOpened(true);
            List<? extends NestedInfo> children = nestedInfo.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                int[] positions = items.get(position);
                positions = Arrays.copyOf(positions, positions.length + 1);
                positions[positions.length - 1] = i;
                items.add(position + 1, positions);
            }
        }
    }
}
