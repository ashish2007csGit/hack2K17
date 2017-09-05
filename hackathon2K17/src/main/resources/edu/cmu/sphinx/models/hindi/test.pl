
##############################################################
# Script for demonstrating use of Hindi Acoustic Models
# Author : Sachin Joshi
##############################################################

system("arecord  -f S16_LE -c1 -r16000 wav/test.wav > log");

# Instead of arecord you use rec command also
#system("rec -r 16000 -s w -f u wav/test.wav");

`$ENV{"SPHINXDIR"}\/src\/programs\/wave2feat -verbose 1 -di wav -ei wav -mswav 1 -do feat -eo mfc -c etc\/hindi_test.fileids 2>> log`;
`./scripts_pl/decode/slave.pl`;

open(f,"result/hindi.match");
$line = <f>;
$line =~ s/\(.*\)//g;
close(f);

open(f,">result/hindi.match");
print f "$line";
close(f);

print("\nRECOGNIZED SENTENCE:  ");
system("cat result/hindi.match");
print("\n");

system("rm feat/test.mfc");
system("mv wav/test.wav wav/recorded.wav");

###############################################################
