POSTGRES_USER=cactus_admin
POSTGRES_PASSWORD=secretword
POSTGRES_DB=cactus


build:
	docker-compose up -d --build --force-recreate
	make log

up:
	docker-compose up -d
	make log

django:
	docker-compose build django
	docker-compose restart django
	make log

log:
	docker-compose logs -f django

migrations:
	docker-compose exec django python manage.py makemigrations

migrate:
	docker-compose exec django python manage.py migrate

shell:
	docker-compose exec django python manage.py shell

bash:
	docker-compose exec django bash