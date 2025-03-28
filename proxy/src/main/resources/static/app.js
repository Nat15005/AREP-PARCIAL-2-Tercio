function loadFactors() {
    const value = document.getElementById('factor').value;
    fetch(`/factors?value=${encodeURIComponent(value)}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('factorResult').textContent =
                JSON.stringify(data, null, 1);
        });
}

function loadPrimes() {
    const value = document.getElementById('prime').value;
    fetch(`/primes?value=${encodeURIComponent(value)}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('primeResult').textContent =
                JSON.stringify(data, null, 1);
        });
}
