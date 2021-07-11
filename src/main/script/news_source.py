class NewsSource:
    ID = -1
    
    def __init__(self, portalName, mainURL, searchURL):
        self.portalName = portalName
        self.mainURL = mainURL
        self.searchURL = searchURL

    def getIDName(self):
        return self.ID

    def getPortalName(self):
        return self.portalName

    def getMainURL(self):
        return self.mainURL

    def getSearchURL(self):
        return self.searchURL

