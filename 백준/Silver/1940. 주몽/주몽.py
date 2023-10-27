import sys

read = lambda: sys.stdin.readline().rstrip()

N = int(read())
M = int(read())
nums = list(map(int, read().split()))
nums.sort()

answer = 0
start, end = 0, N-1

while start < end:
    if nums[start] + nums[end] == M:
        answer += 1
        start += 1
        end -= 1
    elif nums[start] + nums[end] < M:
        start += 1
    else:
        end -= 1

print(answer)