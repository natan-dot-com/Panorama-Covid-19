from flask import Flask, render_template, request
from vaccine_estimator import VaccineEstimator

app = Flask(__name__, template_folder='../resources/com/panorama/html')

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

			x = VaccineEstimator(age, estado, pni)
			x.estimate()
			official = str(x.getOfficialEstimation())
			unofficial = str(x.getUnofficialEstimation())
			print(official, unofficial)

			return render_template("./estimativa.html", official=official, unofficial=unofficial)
	except:
		return render_template("./form.html")


if __name__ == "__main__":
	app.run(port=5000)
