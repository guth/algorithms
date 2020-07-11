import zipfile
zf = zipfile.ZipFile('assets/channel.zip')
number = '90052'

while(True):
    s = zf.open(number+'.txt').read()
    print s
    number = s.split()[-1:][0]
    if not number.isdigit():
        break

print "Collecting comments:"
co = ""
number = '90052'
while(True):
    s = zf.open(number+'.txt').read()
    co += zf.getinfo(number+'.txt').comment
    print s
    number = s.split()[-1:][0]
    if not number.isdigit():
        break
print co

# for info in zf.infolist():
    # print info.filename
    # print '\tComment:\t', info.comment
    # print '\tModified:\t', datetime.datetime(*info.date_time)
    # print '\tSystem:\t\t', info.create_system, '(0 = Windows, 3 = Unix)'
    # print '\tZIP version:\t', info.create_version
    # print '\tCompressed:\t', info.compress_size, 'bytes'
    # print '\tUncompressed:\t', info.file_size, 'bytes'
    # print