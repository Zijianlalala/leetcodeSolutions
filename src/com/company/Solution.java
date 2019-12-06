package com.company;

import org.w3c.dom.ls.LSException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(9%10);
        ListNode l1 = new ListNode(2);
        ListNode t1 = l1;
        t1.next = new ListNode(4);
        t1 = t1.next;
        t1.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        ListNode t2 = l2;
        t2.next = new ListNode(6);
        t2 = t2.next;
        t2.next = new ListNode(4);

        ListNode result = new Solution().addTwoNumbers(l1, l2);
        System.out.println(result);
    }

    /**
     * 计算两个链表对应元素的和，包括进位
     * 两个链表长度一定相等吗
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;//当前计算是否需要进位
        ListNode result = null;
        ListNode current = null;
        int cycle = 1;
        while (l1 != null && l2 != null) {
            int t = l1.val + l2.val + carry;//计算当前节点的结果
            carry = t>=10?1:0;//下回合是否要进位
            if (cycle == 1) {//初始化
                result = new ListNode(t%10);
                current = result;
                cycle++;
            } else {
                ListNode node = new ListNode(t%10);
                current.next = node;
                current = node;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 == null && carry == 1) {
            ListNode node = new ListNode(1);
            current.next = node;
        }
        while (l1 != null) {
            if (carry == 1) {
                l1.val ++;
                carry = 0;
            }
            if (l1.val >= 10) {
                l1.val = l1.val % 10;
                carry = 1;
            }
            ListNode node = new ListNode(l1.val);
            current.next = node;
            l1 = l1.next;
            current = current.next;
        }
        while (l2 != null) {
            if (carry == 1) {
                l2.val ++;
                carry = 0;
            }
            if (l2.val >= 10) {
                l2.val = l2.val % 10;
                carry = 1;
            }
            ListNode node = new ListNode(l2.val);
            current.next = node;
            l2 = l2.next;
            current = current.next;
        }
        return result;
    }
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }


}
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
