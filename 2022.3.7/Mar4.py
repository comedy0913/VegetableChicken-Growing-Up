import random

if __name__ == "__main__":
    for j in range(11):
        nums = []
        # N = int(input("请输入棋子个数："))
        # times = int(input("请输入操作次数："))
        print("\n(x, y) = ("+str(j)+", "+str(j)+")")
        N = j
        times = j
        for i in range(N):
            nums.append(random.randint(0, 1,))
        i = 0

        while i < times:
            temp = []
            for index in range(len(nums) - 1):
                temp.append(nums[index] ^ nums[index + 1])
            temp.append(nums[0] ^ nums[len(nums) - 1])
            if temp == nums:
                break
            else:
                nums = temp
            i = i + 1
            print(str(i) + ": " + str(temp))
