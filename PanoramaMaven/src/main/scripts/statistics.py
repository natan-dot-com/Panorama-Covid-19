import pandas as pd
from flask import Flask, render_template


class Statistics:

	COVID_DATA_URL = 'https://raw.githubusercontent.com/wcota/covid19br/master/cases-brazil-total.csv'
	
	#features de interesse
	FEATURES = ['totalCases', 'deaths', 'date', 'newCases', 'newDeaths', 'deaths_by_totalCases',
            	'totalCases_per_100k_inhabitants', 'deaths_per_100k_inhabitants', 'vaccinated',
            	'vaccinated_second', 'vaccinated_single', 'totalDoses', 'fully_immunized']
	
	df = None # Dataframe para os dados estatísticos de COVID

	def __init__(self) -> None:
		self.df = pd.read_csv(self.COVID_DATA_URL)
		self.featureEngineering()
	
	def featureEngineering(self) -> None:
		# adiciona a feature 'totalDoses' = total de doses ministradas
		self.df['totalDoses'] = self.df[['vaccinated', 'vaccinated_second', 'vaccinated_single']]\
										.sum(axis = 1)
		
		# adiciona a feature 'fully_immunized' = número de pessoas totalmente vacinadas, considerando também a dose única
		self.df['fully_immunized'] = self.df[['vaccinated_second', 'vaccinated_single']].sum(axis = 1)
	
		# transforma a feature 'deaths_by_totalCases', antes do tipo float, em uma string de porcentagem
		self.df['deaths_by_totalCases'] = self.df['deaths_by_totalCases'].mul(100).round(2).astype(str).add('%')

		# transforma a feature 'date', antes do tipo Object, em uma datetime no formato brasileiro
		self.df['date'] = pd.to_datetime(self.df['date']).dt.strftime('%d/%m/%Y')

		# arredondamento das taxas
		self.df['totalCases_per_100k_inhabitants'] = self.df['totalCases_per_100k_inhabitants'].round(2)
		self.df['deaths_per_100k_inhabitants'] = self.df['deaths_per_100k_inhabitants'].round(2)

	def getDataFromState(self, state:str) -> dict:
		'''
			Retorna as colunas contidas em FEATURES como um dicionário, da linha correspondente ao estado state
			{'coluna1' : valor1, ...}
		'''
		data = self.df.loc[self.df['state'] == state, self.FEATURES]
		return data.to_dict('records')[0]
		

# Flask App
statistic = Statistics()
app = Flask(__name__, template_folder='../resources/com/panorama/html')

@app.route('/')
def index():
	# TOTAL corresponde ao dados totais do brasil
	data = statistic.getDataFromState('TOTAL')
	return render_template('./statistics.html', state = 'TOTAL', statistic=data)


if __name__ == '__main__':
	app.run(debug=True, port=5001)
