package cn.joey.cn.joey.leecode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LeeCodeDemo {

    /**
     * 两个链表相加求和
     *
     * @return
     */
    @Test
    public void addTwoNumbers() {
        ListNode l1 = null;
        ListNode l2 = null;

        ListNode dummyHead = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode curr = dummyHead;
        int curry = 0;
        while (p != null || q != null) {
            int a = p != null ? p.val : 0;
            int b = q != null ? q.val : 0;
            int sum = a + b + curry;
            curry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (curry > 0) {
            curr.next = new ListNode(curry);
        }
        System.out.println(dummyHead.next);
    }

    /**
     * 无重复字符的最常子串  LeeCode#3
     *
     * @return
     */
    @Test
    public void lengthOfLongestSubString() {
        String s = "abdcahdjcndjded";
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        System.out.println(ans);
    }

    /**
     * 找出数组中的重复数字 LeeCode#287
     */
    @Test
    public void findDuplicate() {
        int[] nums = {1, 2, 3, 4, 2};
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max = -1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        System.out.println(ans);
    }


    @Test
    public void testDemo(){
        System.out.println(1<<1);
    }
}
