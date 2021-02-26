package com.company.topology;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CourseCanFinish {

    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    /**
     *  * 207 课程表
     *  * 拓扑排序
     *  * 深度搜索
     *
     * @param numCourses 需要学习的科目
     * @param prerequisties  有先后顺序的课程安排
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisties) {
        edges = new ArrayList<>(); //每门课程
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses]; // 没门课程是否 未开始 搜索中 已完成
        for (int[] info : prerequisties) {
            edges.get(info[1]).add(info[0]); // 存储所有的规则 ，index代表 后休课程  value 代表 后休课程前的先修课程
        }
        for (int i = 0; i < numCourses && valid; i++) { //合法状态下遍历所有课程
            if (visited[i] == 0) { //当前课程未开始
                dfs(i); //进行深度搜索
            }
        }
        return valid;
    }

    private void dfs(int u) {
        visited[u] = 1; //调整该课程状态为 搜索中
        for (int v : edges.get(u)) { //获取该课程规则
            if (visited[v] == 0) {  // 课程规则 前置条件 未开始
                dfs(v);   // 搜索 课程规则 前置课程的 课程
                //搜索完毕 证明合规
                if (!valid) {   //如果未转变为非法 直接返回
                    return;
                }
            } else if (visited[v] == 1) { //课程前置课程 也在搜索中 ，证明环形拓扑 无法全部休完
                valid = false;
                return;
            }
        }
        visited[u] = 2; //设置本课程搜索完成
    }
}
