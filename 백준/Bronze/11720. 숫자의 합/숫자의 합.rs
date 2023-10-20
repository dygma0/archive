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
    let ans = lines
        .next()
        .unwrap()
        .trim()
        .bytes()
        .map(|x| x as usize - 48_usize)
        .sum::<usize>();
    writeln!(output, "{}", ans)?;

    Ok(())
}