import sys
input = sys.stdin.readline
N = int(input())
start = 1
end = 1
total = 1
count = 1

if N == 1 or N == 2:
  print(1)
  sys.exit()

while end < N//2 + 2:
  if total == N:
    count += 1
    end += 1
    total = total + end
  elif total < N:
    end += 1
    total = total + end
  else:
    total = total - start
    start += 1
print(count)