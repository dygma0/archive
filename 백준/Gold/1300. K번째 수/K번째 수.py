import sys

N = int(sys.stdin.readline())
K = int(sys.stdin.readline())


def count(number):
    return sum([min(N, (number - 1) // r) for r in range(1, N + 1)])


def solve():
    answer = 0
    left, right = 1, N * N
    while left <= right:
        mid = (left + right) // 2
        distance = count(mid)
        if distance <= K - 1:
            if answer < mid:
                answer = mid
            left = mid + 1
        else:
            right = mid - 1

    return answer


print(solve())
