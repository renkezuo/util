1、读取头信息，返回剩余字节数组
2、如果Content-Length不为空，取该length长度的数据
3、如果Content-Length为空，查看chunked是否存在，不存在返回异常
4、chunked格式              长度，换行，对应长度数据，换行；长度，换行，对应长度数据，换行；...



#测试     访问https://10.10.3.164:18443/nexus/
