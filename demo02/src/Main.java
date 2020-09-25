/*
* 输入（满二叉树）
* 第一行 树的个数 // 3
* 第二行 第一组节点的权值// 1 2 2 1 2 1 2
* 第三行 第二组节点的权值// 1 2 2 1 3 2 2
* 第四行 第三组节点的权值// 1 2 2 1 2 1 3
* 输出
* 若该树存在一个node的左子树权重 = 右子树权重 则输出“Yes” 否则 输出 “No”
* 案例
* 输入：
* 3
* 1 2 2 1 2 1 2
* 1 2 2 1 3 2 2
* 1 2 2 1 2 1 3
* 输出
* "Yes"
* "Yes"
* "No"
*
* */
import java.util.*;

public class Main {
    // 树上是否有节点满足
    private static boolean hasTree;
    // TreeNode 类
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        //将hasTree 初始化为false
        hasTree = false;
        //处理输入
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        String[] inputs = new String[num];
        for (int i= 0; i<num;i++){
            String inputString = in.nextLine();
            inputs[i] = inputString;
        }
        // 计算各树是否满足条件
        for (int i=0; i<num;i++){
            String[] strArr = inputs[i].split(" ");
            //建立树
            TreeNode root = buildTree(strArr);
            //计算树各节点权值（若发现符合，则修改hasTree为true）
            treeVal(root);
            if (hasTree){
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
            //重新初始化 hasTree
            hasTree = false;
        }
//        for(String s : inputs){
//            String[] strArr = s.split(" ");
//            TreeNode root = buildTree(strArr);
//            dfs(root);
//        }
    }

//    private static void dfs(TreeNode root){
//        if (root == null){
//            return;
//        }
//        System.out.print(root.val + " ");
//        dfs(root.left);
//        dfs(root.right);
//    }

    //递归 计算树各子树权值并检测是否有节点左右子树权值相等
    private static int treeVal(TreeNode root){
        if (root.left == null){
            return root.val;
        }
        int left = treeVal(root.left);
        int right = treeVal(root.right);
        if (left == right){
            hasTree = true;
        }
        return left+right+root.val;
    }

    //构造二叉树
    private static TreeNode buildTree(String[] strArr){
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        boolean left = false;
        int val1 = Integer.valueOf(strArr[0]);
        TreeNode root = new TreeNode(val1);
        TreeNode cur = root;
        /*
        * 妈了个比老子写错了一行！之前把root加到队列里，代码改了以后忘了删这行！！！debug半天没看出来！！！我是nt！！！
        * 笔试凉了！！！我是nt！！！！
        */
//        nodeQueue.offer(root);
        for (int i=1;i<strArr.length;i++){
            int val = Integer.valueOf(strArr[i]);
            TreeNode n = new TreeNode(val);
//            System.out.println("添加前 queue的长度： "+ nodeQueue.size() );
            nodeQueue.offer(n);
//            System.out.println("添加后 queue的长度： "+ nodeQueue.size() );
//            System.out.println("i = " + i);
//            System.out.println("left is null : "+ (cur.left == null));
//            System.out.println("right is null : "+ (cur.right == null));

            if (cur.left == null){
                cur.left = n;
            }
            else if (cur.right == null){
                cur.right = n;
                cur = nodeQueue.poll();
//                System.out.println("取出后 queue的长度： "+ nodeQueue.size());
//                System.out.println("cur.val = " + cur.val);
            }
//            System.out.println("cur.val = " + cur.val);

        }
        return root;
    }

}
