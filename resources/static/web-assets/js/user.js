function updateProfile() {
    const name = document.getElementById('name').value;
    const data = {
        name: name
    };

    axios.put('/api/users/update-profile', data)
        .then(response => {
            toastr.success('Cập nhât thông tin thành công!');
        })
        .catch(error => {
            toastr.error('Error updating profile: ' + error.response.data);
            console.error('Error:', error);
        });
}