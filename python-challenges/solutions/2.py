# Hint from page source: "find rare characters in the mess below"
s = ""

# input has 1220 lines
for x in xrange(1220):
	s += raw_input()

ans = [c for c in s if c.isalpha()]
print ans