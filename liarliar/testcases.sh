#!/bin/bash


runtest() {
	if [ ! -f "$1" ] 
	then
		echo "File $1 does not exist"
		return -1
	fi
	
	echo "$ time ./liarliar $1"
	time ./liarliar $1
	echo ""
}

runtest "samples/lltest1.in"
runtest "samples/lltest2.in"
runtest "samples/mm0.in"
runtest "samples/mm42.in"
runtest "samples/c350k.in"
runtest "samples/raluca29.in"
runtest "samples/gabor-vizi-my33.in"
runtest "samples/1k.in"
runtest "samples/1k_100.in"
runtest "samples/4.1.in"
runtest "samples/5.in"
runtest "samples/10k_1k.in"
