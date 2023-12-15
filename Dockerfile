# Menggunakan base image Python
FROM python:3.8-slim

# Set working directory di dalam container
WORKDIR /app

# Menyalin file requirements.txt ke dalam container
COPY requirements.txt .

# Menginstal dependensi aplikasi
RUN pip install --no-cache-dir -r requirements.txt

# Menyalin seluruh konten proyek ke dalam container
COPY . .

# Menjalankan aplikasi Flask pada port 5000
CMD ["python", "app.py"]