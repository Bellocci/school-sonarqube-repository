Occorre inserire i seguenti comandi per eseguire SonarQube
utilizzando il comando
	docker-compose up

sudo sysctl -w vm.max_map_count=262144
sudo sysctl -w fs.file-max=65536
ulimit -n 65536
ulimit -u 4096
