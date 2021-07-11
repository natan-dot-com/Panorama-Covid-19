import sqlite3
from news_instance import NewsInstance
from news_source import newsSource


class newsFeedDatabase:

	DB_LOCATION = 'paranorama_covid.sqlite'
	connection = None 
	cursor = None

	def __init__(self) -> None:
		self.connection = sqlite3.connect(self.DB_LOCATION)
		self.cursor = self.connection.cursor()
	
	def close(self) -> None:
		if self.connection:
			self.connection.commit()
			self.cursor.close()
			self.connection.close()
	
	def __enter__(self) -> None:
		return self
	
	def __exit__(self, ext_type, exc_value, traceback) -> None:
		self.close()
	
	def create_table(self) -> None:
		self.cursor.executescript('''
		CREATE TABLE IF NOT EXISTS Source(
			id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
			name TEXT UNIQUE,
			mainURL TEXT,
			searchURL TEXT
		);
		
		CREATE TABLE IF NOT EXISTS News(
			id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
			title TEXT,
			subtitle TEXT,
			newsDate TEXT,
			url TEXT UNIQUE,
			source_id INTEGER,
			dataAdded TEXT
		);
		''')
	
	def insertOnSource(self, source : newsSource) -> None:
		name = source.getPortalName()
		mainURL = source.getMainURL()
		searchURL = source.getSearchURL()

		self.cursor.execute('''INSERT OR IGNORE INTO Source (name, mainURL, searchURL)
							VALUES (?, ?, ?)''',
							(name, mainURL, searchURL))
	
	def insertOnNews(self, source: NewsInstance) -> None:
		title = source.getNewsTitle()
		subtitle = source.getNewsSubtitle()
		newsDate = source.getNewsDate()
		url = source.getNewsURL()
		dataAdded = source.getNewsDataAdded().isoformat()
		nameSource = source.getNewsSource().getPortalName()
		self.cursor.execute('SELECT id FROM Source WHERE name = ?', (nameSource, ))
		source_id = self.cursor.fetchone()[0]

		self.cursor.execute('''INSERT OR IGNORE INTO News 
							(title, subtitle, newsDate, url, source_id, dataAdded)
							VALUES (?, ?, ?, ?, ?, ?)
							''', (title, subtitle, newsDate, url, source_id, dataAdded))

	def insertON(self, news : NewsInstance) -> None:
		self.insertOnSource(news.getNewsSource())
		self.insertOnNews(news)

	
	def query(self, query : str) -> list:
		self.cursor.execute(query)
		return self.cursor.fetchall()
