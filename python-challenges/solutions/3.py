import re
# "One small letter, surrounded by EXACTLY three big bodyguards on each of its sides"
s = open('assets/3.in').read().replace("\n", "")

ans = ""
i = 4
while i < len(s)-4:
	ll = s[i-4]
	l = s[i-3:i]
	r = s[i+1:i+4]
	rr = s[i+4]
	if s[i].islower() and ll.islower() and l.isupper() and r.isupper() and rr.islower():
		ans += s[i]
	i += 1
print ans

# Or, with regexes:
regex = "[a-z][A-Z]{3}[a-z][A-Z]{3}[a-z]"
matches = re.findall(regex, s)
ans = ""
for match in matches:
	ans += match[4]
print ans
