# a = [1, 11, 21, 1211, 111221, 
# find len(a[30])
def next(s):
	i = 0
	j = 0
	ans = ""
	while i < len(s):
		while i<len(s) and j < len(s) and s[i] == s[j]:
			j += 1
		count = j-i
		ans += str(count) + s[i]
		i = j
	return ans

s = '1' # a[0]
for x in xrange(30):
	s = next(s)
print len(s)