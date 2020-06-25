kill -9 $(jps -l | grep com.demo | awk '{print $(NF-1)}')
