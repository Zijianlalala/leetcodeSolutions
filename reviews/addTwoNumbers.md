# addTwoNumbers
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:
```
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
```

## 我的代码
巨长无比，没有提前考虑会有各种情况的发生，比如两个链表的长度不同。所以基本上是写一遍(，错一遍，改一遍)$\times 3$。虽然最后结果差强人意，但是看到大佬的代码就给跪了。

思路就是操纵两个链表，使其对应节点的值相加，如果产生进位就在下一个节点相加时$+1$

最开始结果链表的初始化是放到`while`循环外面。后来发现他的设置与循环体内的设置不同，又有相似之处，就都放到循环体里面，用一个`cycle`进行区分，但大佬的代码先初始化了一个空的头结点，后续操作对头结点的子结点进行。所以真头结点的操作跟其他结点的操作保持一致了（复习数据结构还背过）

输入的两个链表长度不同的请款，我另写了两个`while`循环，但大佬把所有情况的讨论都放到了同一个`while`中，适用性很强。
```
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
```

## 大佬代码
该大佬称已经拿到谷歌offer了，刷题是必要条件。

```
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;//这里表示是否进位，不需要其他标志位了
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;
    }
}
```