给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    int[] res;
    public List<Integer> countSmaller(int[] nums) {
        res=new int[nums.length];
        int[] mapper=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            mapper[i]=i;
        }
        mergeSort(nums,0,nums.length-1,mapper);
        List<Integer> list=new ArrayList<>();
        for(int i:res){
            list.add(i);
        }
        return list;
    }
    private void mergeSort(int[] nums,int left,int right,int[] mapper){
        if(left>=right){
            return;
        }
        int mid=left+(right-left)/2;
        mergeSort(nums,left,mid,mapper);
        mergeSort(nums,mid+1,right,mapper);
        merge(nums,left,mid,right,mapper);
    }

    private void merge(int[] nums,int left,int mid,int right,int[] mapper){
        int i=left;
        int j=mid+1;
        int len=right-left+1;
        int[] tmp=new int[len];
        int k=0;
        while(i<=mid&&j<=right){
            if(nums[mapper[i]]<=nums[mapper[j]]){
                res[mapper[i]]+=j-mid-1;
                tmp[k++]=mapper[i++];
            }else{
                tmp[k++]=mapper[j++];
            }
        }
        while(i<=mid){
            res[mapper[i]]+=right-mid;
            tmp[k++]=mapper[i++];
        }
        while(j<=right){
            tmp[k++]=mapper[j++];
        }
        System.arraycopy(tmp,0,mapper,left,len);
    }
}