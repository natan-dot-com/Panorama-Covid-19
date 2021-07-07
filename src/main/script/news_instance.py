from datetime import datetime
from datetime import timedelta

class newsInstance:
    ID = -1

    def __init__(self, newsTitle, newsURL, newsSubtitle, newsDate, newsSource):
        self.newsTitle = newsTitle
        self.newsURL = newsURL
        self.newsSubtitle = newsSubtitle
        self.newsDate = newsDate
        self.newsSource = newsSource
        self.dataAdded = datetime.now()

    def getNewsID(self):
        return self.ID

    def getNewsTitle(self):
        return self.newsTitle

    def getNewsURL(self):
        return self.newsURL

    def getNewsSubtitle(self):
        return self.newsSubtitle

    def getNewsDate(self):
        return self.newsDate

    def getNewsSource(self):
        return self.newsSource

    def getNewsDataAdded(self):
        return self.dataAdded

    def isExpired(self):
        return datetime.now() > self.dataAdded + timedelta(days=7)
