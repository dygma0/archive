use std::{io, error};
use std::io::{Read, Write};

fn main() -> Result<(), Box<dyn error::Error>> {
    let stdin = io::stdin();
    let stdout = io::stdout();
    let mut input = stdin.lock();
    let mut output = io::BufWriter::new(stdout.lock());

    let mut buf = String::new();
    input.read_to_string(&mut buf)?;
    let mut lines = buf.lines().skip(1);

    let numbers = lines
        .next()
        .unwrap()
        .trim()
        .split_whitespace()
        .map(|x| x.parse::<f32>().unwrap())
        .collect::<Vec<f32>>();

    let max_value = numbers.iter().cloned().fold(0./0., f32::max);

    let numbers = numbers
        .iter()
        .map(|x| x / max_value * 100_f32)
        .collect::<Vec<f32>>();

    let ans: f32 = numbers.iter().sum::<f32>() / numbers.len() as f32;
    writeln!(output, "{}", ans).unwrap();

    Ok(())
}