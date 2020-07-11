import string

alpha = "abcdefghijklmnopqrstuvwxyz"
newAlpha = "cdefghijklmnopqrstuvwxyzab"
trans = string.maketrans(alpha, newAlpha)

text = "g fmnc wms bgblr rpylqjyrc gr zw fylb. rfyrq ufyr amknsrcpq ypc dmp. bmgle gr gl zw fylb gq glcddgagclr ylb rfyr'q ufw rfgq rcvr gq qm jmle. sqgle qrpgle.kyicrpylq() gq pcamkkclbcb. lmu ynnjw ml rfc spj."

newText = text.translate(trans)
print newText

url = "map"
newUrl = url.translate(trans)
print newUrl