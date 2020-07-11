import itertools

perm = itertools.permutations('123456789', 9)

good = set()
count = 0

while True:
	try:
		count = count + 1
		l = perm.next()
		s = ""

		for item in l:
			s += item
		s = str(s)

		for a in range(1,8):
			for b in range(a+1,9):
				p = int(s[:a])
				q = int(s[a:b])
				prod = int(s[b:])

				if p*q == prod:
					good.add(prod)

	except StopIteration:
		break

print 'Total sum: %d' % sum(good)