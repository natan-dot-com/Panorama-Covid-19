from database import newsFeedDatabase
from info_scrap import CNN
from info_scrap import UOL

FIRST_QUERY ='''SELECT News.title, News.url, News.subtitle, News.newsDate, Source.name
                FROM News
                JOIN Source ON News.source_id == Source.id 
                WHERE %s == News.source_id
                ORDER BY dataAdded DESC
                LIMIT 4;'''

def main():
	with newsFeedDatabase() as db:
		db.create_table()
		for news in CNN():
			db.insertON(news)

		for news in UOL():
			db.insertON(news)
		
		db.removeOldest('-7 day')



if __name__ == '__main__':
	main()