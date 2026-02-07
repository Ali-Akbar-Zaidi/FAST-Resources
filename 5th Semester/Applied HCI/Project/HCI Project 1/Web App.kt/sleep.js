const form = document.querySelector('form');
const chart = document.querySelector('.chart');

let data = [];

const storedData = localStorage.getItem('data');
if (storedData) {
  data = JSON.parse(storedData);
}

form.addEventListener('submit', (event) => {
  event.preventDefault();

  const dateInput = form.querySelector('#date');
  const hoursInput = form.querySelector('#hours');

  // Validate date (cannot select future date)
  const currentDate = new Date().toISOString().split('T')[0];
  if (dateInput.value > currentDate) {
    // Automatically set date to today
    dateInput.value = currentDate;
  }

  // Validate hours (cannot enter negative or decrease after 0)
  let hours = parseInt(hoursInput.value, 10);
  if (hours < 0) {
    // Automatically set hours to 0
    hours = 0;
  }

  const date = dateInput.value;

  data.push({ date, hours });
  localStorage.setItem('data', JSON.stringify(data));
  updateChart();
});

const clearButton = document.querySelector('#clear-button');

clearButton.addEventListener('click', () => {
  localStorage.removeItem('data');
  data = [];
  updateChart();
});

function updateChart() {
  const labels = [];
  const values = [];

  data.forEach((datum) => {
    labels.push(datum.date);
    values.push(datum.hours);
  });

  chart.innerHTML = '';

  const context = chart.getContext('2d');
  const chartData = {
    labels,
    datasets: [{
      label: 'Hours of Sleep',
      data: values,
      backgroundColor: '#00b894',
      borderColor: '#008b77',
      fill: false,
    }],
  };
  new Chart(context, {
    type: 'line',
    data: chartData,
    options: {
      responsive: true,
      maintainAspectRatio: true,
    },
  });
}
