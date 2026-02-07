// dashboard.js
document.addEventListener('DOMContentLoaded', function () {
    const chart = document.querySelector('.chart');
    const sleepCount = document.querySelector('#sleep-count span');

    let data = [];

    const storedData = localStorage.getItem('data');
    if (storedData) {
        data = JSON.parse(storedData);
        updateChart();
        updateSleepCount();
    }

    function updateChart() {
        const labels = [];
        const values = [];

        data.forEach((datum) => {
            labels.push(datum.date);
            values.push(datum.hours);
        });

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

    function updateSleepCount() {
        sleepCount.textContent = data.length;
    }
});
