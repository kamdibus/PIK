const hostname = window && window.location && window.location.hostname;

let url;

if (hostname === 'localhost') {
    url = 'http://localhost:8080';
} else if (hostname === 'wtxgs.herokuapp.com') {
    url = 'http://wtxgsb.herokuapp.com';
}

export default url;