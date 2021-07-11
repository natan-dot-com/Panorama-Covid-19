from database import newsFeedDatabase
from scrap import CNN

def main():
	with newsFeedDatabase() as db:
		db.create_table()
		for news in CNN():
			db.insertON(news)

		print('All registers from News table', db.query('SELECT * FROM News'))
		print('All registers from Source table', db.query('SELECT * FROM Source'))


if __name__ == '__main__':
	main()