from flask import Flask, render_template, request
from vaccine_estimator import VaccineEstimator

app = Flask(__name__, template_folder='../resources/com/panorama/html', static_folder='../resources/com/panorama/css')

@app.route('/')
def index():
	return render_template('./form.html')


@app.route('/', methods=['POST'])
def getvalue():
	try:
		if request.method == 'POST':
			age = request.form['age']
			estado = request.form['state']
			pni = request.form['pni']
			print(age, estado, pni)

			estimation = VaccineEstimator(age, estado, pni)
			estimation.estimate()
			official = str(estimation.getOfficialEstimation())
			unofficial = str(estimation.getUnofficialEstimation())

			return render_template("./estimativa.html", official=official, unofficial=unofficial)
	except:
		return render_template("./form.html")


if __name__ == "__main__":
	app.run(port=5000)
