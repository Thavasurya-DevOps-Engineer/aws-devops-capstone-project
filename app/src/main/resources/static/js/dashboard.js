// CPU Usage Chart

const cpuCtx = document.getElementById('cpuChart');

if (cpuCtx) {

    new Chart(cpuCtx, {

        type: 'line',

        data: {

            labels: ['10 AM', '11 AM', '12 PM', '1 PM', '2 PM', '3 PM'],

            datasets: [{

                label: 'CPU %',

                data: [35, 42, 38, 55, 49, 62],

                borderColor: '#0d6efd',

                backgroundColor: 'rgba(13,110,253,0.15)',

                fill: true,

                tension: 0.4

            }]

        },

        options: {

            responsive: true,

            plugins: {

                legend: {

                    labels: {

                        color: '#ffffff'

                    }

                }

            },

            scales: {

                x: {

                    ticks: {

                        color: '#ffffff'

                    }

                },

                y: {

                    ticks: {

                        color: '#ffffff'

                    }

                }

            }

        }

    });

}



// Memory Chart

const memoryCtx = document.getElementById('memoryChart');

if (memoryCtx) {

    new Chart(memoryCtx, {

        type: 'doughnut',

        data: {

            labels: [

                'Used',

                'Free'

            ],

            datasets: [{

                data: [

                    68,

                    32

                ],

                backgroundColor: [

                    '#0d6efd',

                    '#20c997'

                ]

            }]

        },

        options: {

            responsive: true,

            plugins: {

                legend: {

                    labels: {

                        color: '#ffffff'

                    }

                }

            }

        }

    });

}